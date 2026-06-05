// 비순차적 전송
// 1g 12초
import axios from 'axios';
import {sha256} from 'js-sha256';
const fileUploadUtils = {
    install: (app, options) => {
        const mb = 1024 * 1024 * 10;
        const upload = {
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

                let fileKey = await uploadChunkFile(inputData);
                if(fileKey){
                    inputData.fileKey=fileKey;
                    inputData.currentChunk++;

                    let results=[];
                    for(inputData.currentChunk; inputData.currentChunk<inputData.totalChunks-1; inputData.currentChunk++){
                        results.push(call(inputData));
                    }    
                    Promise.all(results).then(response=>{
                        if(inputData.currentChunk===inputData.totalChunks-1) {
                            uploadChunkFile(inputData);
                            // let blob=inputData.fileInfo.slice(0,inputData.fileInfo.size);
                            // let newFile=new File([blob], inputData.fileKey, {type: inputData.fileInfo.type});
                            // newFile.arrayBuffer().then((rs)=>{
                            //     let fileChecksum = sha256.update(rs).hex();
                            //     inputData.fileChecksum=fileChecksum;
                            //     uploadChunkFile(inputData);
                            // });
                        }
                    });

                }
            }
        }

        const uploadChunkFile = async(inputData) => {
            let response =await call(inputData);
            return response.data.resultData;       
        }

        const call=async(inputData)=>{
            let sendData = await setInput(inputData);
            return axios.post(`/mongo/file/upload/async`,sendData,{ headers: { 'Content-Type': 'multipart/form-data'} });
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

            if(inputData.fileChecksum) rs.append("fileChecksum",inputData.fileChecksum);
            return rs;
        }

        app.config.globalProperties.$fileUpload = upload;
    }


}
export default fileUploadUtils;


/**
 *  호출
    let send = {
        fileInfo: fileInfo,
        folderId: dataset.value.folderDetail.folderId,
        userInfo: proxy.$globalInstance.user,
    }
    proxy.$fileUpload.prepare(send);

 */

/**
 * filechecksum
 if(sendData.has('fileKey')){
    let blob=inputData.fileInfo.slice(0,inputData.fileInfo.size);
    let newFile=new File([blob], sendData.fileKey, {type: inputData.fileInfo.type});
    inputData.fileInfo=newFile;
}
await inputData.fileInfo.arrayBuffer().then((rs)=>{
    let fileChecksum = sha256.update(rs).hex();
    sendData.append("fileChecksum",fileChecksum);
    return getPost(sendData);
});
 */