import { useEffect, useState } from 'react';
import request from '../api/request';
import '../styles/goods.css';

export default function GoodsTable() {
    const [data, setData] = useState([]);
    const [total, setTotal] = useState(0);
    const [page, setPage] = useState(1);

    const fetchData = (p = 1) => {
        request
            .get('/goods', { params: { page: p, size: 10 } })
            .then((res) => {
                console.log('res.data ->', res.data);

                // 兼容两种结构：直接分页对象 或 Result.success(分页对象)
                const pageData = res.data.data ? res.data.data : res.data;

                setData(pageData.records || []);
                setTotal(pageData.total || 0);
            })
            .catch((err) => {
                console.error('Failed to fetch data:', err);
                setData([]);
                setTotal(0);
            });
    };

    useEffect(() => {
        fetchData(page);
    }, [page]);

    const pages = Math.ceil(total / 10) || 1;

    return (
        <>
            <table className="goods">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>入库时间</th>
                    <th>种类</th>
                    <th>备注</th>
                </tr>
                </thead>
                <tbody>
                {data.length ? (
                    data.map((g) => (
                        <tr key={g.id}>
                            <td>{g.id}</td>
                            <td>{g.inTime}</td>
                            <td>{g.type}</td>
                            <td>{g.remark}</td>
                        </tr>
                    ))
                ) : (
                    <tr>
                        <td colSpan="4">暂无数据</td>
                    </tr>
                )}
                </tbody>
            </table>

            <div className="pager">
                {Array.from({ length: pages }, (_, i) => i + 1).map((n) => (
                    <button
                        key={n}
                        className={n === page ? 'active' : ''}
                        onClick={() => setPage(n)}
                    >
                        {n}
                    </button>
                ))}
            </div>
        </>
    );
}
