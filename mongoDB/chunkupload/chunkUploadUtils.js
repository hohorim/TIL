import axios from 'axios';
import {sha256} from 'js-sha256';
const chunkUploadUtils = {
    install: (app, options) => {
        const mb = 1024 * 1024 * 10;

        const chunkUpload = {
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
                
                let response = await uploadChunkFile(inputData);
                if (response.data.resultData){
    
                    inputData.fileKey = response.data.resultData;
                    inputData.currentChunk++;

                    let requests = [];
                    for (inputData.currentChunk; inputData.currentChunk < inputData.totalChunks-1; inputData.currentChunk++) {
                        requests.push(uploadChunkFile(inputData));
                    }
                    
                    await Promise.all(requests).then(response=>{
                        if(inputData.currentChunk===inputData.totalChunks-1){
                            inputData.currentChunk =inputData.totalChunks-1;

                            uploadChunkFile(inputData);
                        }
                    });
                }
            }
        }

        const uploadChunkFile = (inputData) => {
            
            const sendData = setInput(inputData);
            let response = axios.post(
                `/mongo/upload`,
                sendData,
                { headers: { 'Content-Type': 'multipart/form-data'} }
            );
            return response;
        }

        const setInput = (inputData) => {
            const rs = new FormData();
            rs.append("tenantId", inputData.tenantId);
            rs.append("ownerId", inputData.ownerId);
            rs.append("folderId", inputData.folderId);
            rs.append("totalChunks", inputData.totalChunks);
            rs.append("fileKey", inputData.fileKey);
            rs.append("currentChunk", inputData.currentChunk);
            
            let start = inputData.currentChunk * mb;
            let end = Math.min(start + mb, inputData.fileInfo.size);
            let chunkFile = inputData.fileInfo.slice(start, end);

            rs.append("uploadFile", chunkFile, inputData.fileName);
            // rs.append("fileChecksum",await getChecksum(chunkFile));
            
            return rs;
        }

        const getChecksum = async(file)=>{
            
            let arraybuffer = await file.arrayBuffer();
            
            let fileChecksum = sha256.update(arraybuffer).hex();

            return fileChecksum;
        }

        app.config.globalProperties.$chunkUpload = chunkUpload;
    }


}
export default chunkUploadUtils;


/**
 *  호출
 *         const createFile2 = async (fileInfo) => {
            let send = {
                fileInfo: fileInfo,
                folderId: dataset.value.folderDetail.folderId,
                userInfo: proxy.$globalInstance.user,
            }
            proxy.$chunkUpload.prepare(send);
        }

 */