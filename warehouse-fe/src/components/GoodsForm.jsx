import { useState } from 'react';
import dayjs from 'dayjs';
import request from '../api/request';

export default function GoodsForm() {
    const [form, setForm] = useState({ type: '', inTime: '', remark: '' });

    const submit = e => {
        e.preventDefault();
        request.post('/goods', {
            ...form,
            inTime: dayjs(form.inTime).format('YYYY-MM-DDTHH:mm:ss')
        }).then(() => {
            alert('添加成功');
            setForm({ type: '', inTime: '', remark: '' });
        });
    };

    return (
        <form onSubmit={submit} className="goods-form">
            <label>种类
                <input value={form.type}
                       onChange={e => setForm({ ...form, type: e.target.value })}/>
            </label>
            <label>入库时间
                <input type="datetime-local"
                       value={form.inTime}
                       onChange={e => setForm({ ...form, inTime: e.target.value })}/>
            </label>
            <label>备注
                <input value={form.remark}
                       onChange={e => setForm({ ...form, remark: e.target.value })}/>
            </label>
            <button type="submit">提交</button>
        </form>
    );
}
