import React from 'react'

const Form = React.memo(({ inputTodo, setTodoData, setInputTodo }) => {

    const handleChange = (e) => {
        setInputTodo(e.target.value);
    }

    const handleSubmit = (e) => {
        e.preventDefault();

        let item = {
            id: Date.now(),
            title: inputTodo,
            completed: false
        };
        setInputTodo('');
        setTodoData(x => [...x, item])
    }


    return (
        <div>
            <form onSubmit={handleSubmit} className='flex pt-2'>
                <input className='w-full px-3 py-2 mr-4 text-gray-500 border rounded shadow' type='text' name="value" value={inputTodo} onChange={handleChange} />
                <input className='p-2 text-blue-400 border-2 border-blue-400 rounded hover:text-white hover:bg-blue-200' type='submit' value="입력" />
            </form>
        </div>
    )
})
export default Form