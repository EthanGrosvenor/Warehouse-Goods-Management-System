import { useState } from 'react';
import GoodsTable from './components/GoodsTable';
import GoodsForm from './components/GoodsForm';

export default function App() {
    const [page, setPage] = useState('list');

    return (
        <div style={{ padding: '20px' }}>
            <div style={{ marginBottom: '16px' }}>
                <button onClick={() => setPage('list')}>查看列表</button>
                <button onClick={() => setPage('add')} style={{ marginLeft: '10px' }}>新增货物</button>
            </div>

            {page === 'list' ? <GoodsTable /> : <GoodsForm />}
        </div>
    );
}
