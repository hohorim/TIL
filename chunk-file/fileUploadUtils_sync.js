// 순차적 전송
import axios from 'axios';
import {sha256} from 'js-sha256';
const fileUploadUtils = {
    install: (app, options) => {
        const mb = 1024 * 1024 * 10;
        const fileUpload = {
            prepare: async (request) => {

                let { folderId, fileInfo, userInfo } = request;

                let inputData = {
                    tenantId: userInfo.tenantId,
                    ownerId: userInfo.userId,
                    folderId: "660a31b9079a9e66c3849a38",
                    // folderId: folderId,
                    fileInfo: fileInfo,
                    fileName: fileInfo.name,
                    totalChunks: Math.ceil(fileInfo.size / mb),
                    currentChunk: 0,
                }

                uploadChunkFile(inputData);
            }
        }

        const uploadChunkFile = async(inputData) => {
            let sendData = await setInput(inputData);
            axios.post(`/mongo/file/upload/sync`,sendData,{ headers: { 'Content-Type': 'multipart/form-data'} }).then(response=>{
                if (response.data.resultData && response.status===206){
                    inputData.fileKey=response.data.resultData;
                    inputData.currentChunk++;
                    uploadChunkFile(inputData);
                }
            })
        }

        const setInput = async(inputData) => {
            let rs = new FormData();
            rs.append("tenantId", inputData.tenantId);
            rs.append("ownerId", inputData.ownerId);
            rs.append("folderId", inputData.folderId);
            rs.append("totalChunks", inputData.totalChunks);
            if(inputData.fileKey) rs.append("fileKey", inputData.fileKey);
            rs.append("currentChunk", inputData.currentChunk);
            
            let start = inputData.currentChunk * mb;
            let end = Math.min(start + mb, inputData.fileInfo.size);
            let chunkFile = inputData.fileInfo.slice(start, end);
            rs.append("uploadFile", chunkFile, inputData.fileName);
            return rs;
        }

        app.config.globalProperties.$fileUpload = fileUpload;
    }


}
export default fileUploadUtils;


/**
 *  호출
 *         const createFile2 = async (fileInfo) => {
            let send = {
                fileInfo: fileInfo,
                folderId: dataset.value.folderDetail.folderId,
                userInfo: proxy.$globalInstance.user,
            }
            proxy.$fileUpload.prepare(send);
        }

 */