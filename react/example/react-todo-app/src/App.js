import './App.css';
import React, {useCallback, useState} from 'react';
import Lists from './components/Lists';
import Form from './components/Form';

export default function App() {
    const [todoData, setTodoData] = useState(
        [{
            id: 'test',
            title: '공부하기',
            completed: false
        }],
    );

    const [inputTodo, setInputTodo] = useState("");

    const handleClick = useCallback((id) => {
        let items = todoData.filter(x => x.id !== id);
        setTodoData(items);
    },[todoData]);

    const handleAllClick=()=>{
        setTodoData([]);
    }

    return (
        <div className='flex items-center justify-center w-screen h-screen bg-blue-100'>
            <div className='w-full p-6 m-4 bg-white rounded shadow-sm lg:w-3/4 lg:max-w-lg'>
                <div className='flex justify-between mb-3'>
                    <h1>할일목록</h1>
                    <button onClick={handleAllClick}>모두 지우기</button>
                </div>
                <Lists todoData={todoData} setTodoData={setTodoData} handleClick={handleClick}></Lists>
                
                <Form inputTodo={inputTodo} setTodoData={setTodoData} setInputTodo={setInputTodo}></Form>
                

            </div>
        </div>
    );
}
