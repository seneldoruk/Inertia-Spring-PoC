import {Link, router} from "@inertiajs/react";
import "./App.css";

function Counter({id, updated, value}) {
    const path = window.location.pathname;

    return (
        <>
            <h1>Spring + Inertia</h1>
            <div className="card">
                {value && <h2>Count is {value}</h2>}
                <button onClick={() => router.post(path)} style={{marginRight: 4}}>
                    Increase
                </button>
                <button onClick={() => router.delete(path)} style={{marginLeft: 4}}>
                    Decrease
                </button>
            </div>
            <Link href={
                path === "/counter/1" ? "/counter/2" : "/counter/1"
            }>
                <button>
                    Show Counter {path === "/counter/1" ? 2 : 1}
                </button>
            </Link>

        </>
    );
}

export default Counter;
