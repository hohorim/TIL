import React, { useState } from 'react'

const List = React.memo(({ todoData, setTodoData, provided, snapshot, item, handleClick }) => {

    const [isEdit, setIsEdit] = useState(false);
    const [targetEditText, setTargetEditText] = useState(item.title);

    const handleChangeComplete = (id) => {
        let items = todoData.map(x => {
            if (x.id === id) x.completed = !x.completed;
            return x;
        })

        setTodoData(items);
    }

    const setEdit =(param)=>{
        if(!param){
            let items = todoData.map(x => {
                if (x.id === item.id) x.title = targetEditText;
                return x;
            })

            setTodoData(items);
        }
        setIsEdit(param);
    }

    const handleEditChange=(event)=>{
        setTargetEditText(event.target.value);
    }

    if(isEdit){
        return (
            <div>
                <div className={`bg-gray-100 flex items-center justify-between w-full px-4 py-1 my-2 text-gray-600  border rounded`}>
                    <div className='items-center'>
                        <input className='w-full px-3 py-2 mr-4 text-gray-500 rounded' value={targetEditText} onChange={handleEditChange}/>
                    </div>
                    <div className='items-center'>
                        <button className='px-4 py-2 float-right' onClick={() => setEdit(false)}>save</button>
                    </div>
                </div>
            </div>
        )
    }else{

        return (
          <div>
              <div key={item.id} {...provided.draggableProps} ref={provided.innerRef} {...provided.dragHandleProps}
              className={`${snapshot.isDragging ? 'bg-gray-400' : 'bg-gray-100'} flex items-center justify-between w-full px-4 py-1 my-2 text-gray-600  border rounded`}>
                  <div className='items-center'>
                      <input type='checkbox' defaultChecked={item.completed} onChange={() => handleChangeComplete(item.id)} />
                      <span className={item.completed ? 'line-through' : undefined}>{item.title}</span>
                  </div>
                  <div className='items-center'>
                      <button className='px-4 py-2 float-right' onClick={() => handleClick(item.id)}>x</button>
                      <button className='px-4 py-2 float-right' onClick={() => setEdit(true)}>edit</button>
                  </div>
              </div>
          </div>
        )
    }

})
export default List;