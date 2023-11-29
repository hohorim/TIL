import React from 'react'
import { DragDropContext, Draggable, Droppable } from 'react-beautiful-dnd'
import List from './List';


const Lists = React.memo(({ todoData, setTodoData, handleClick }) => { // 괄호안에 props 로도 받을 수 있음
    const handleEnd = (result)=>{
        if(!result.destination) return false;
        
        let rs = [...todoData];
        let [items] = rs.splice(result.source.index, 1);
        rs.splice(result.destination.index, 0, items);

        setTodoData(rs);
    }

    return (
        <div>
            <DragDropContext onDragEnd={handleEnd}>
                <Droppable droppableId='test-todo'>
                    {(provided)=>(
                        <div {...provided.droppableProps} ref={provided.innerRef}>
                            {todoData.map((x,index) =>
                                <Draggable
                                    key={x.id}
                                    draggableId={x.id.toString()}
                                    index={index}
                                >
                                    {(provided, snapshot)=>(
                                        
                                        <List todoData={todoData} setTodoData={setTodoData} provided={provided} snapshot={snapshot} item={x} handleClick={handleClick}></List>
                                    )}
                                </Draggable>
                            )}
                            {provided.placeholder}
                        </div>
                    )}
                </Droppable>
            </DragDropContext>
        </div>
    )
})
export default Lists