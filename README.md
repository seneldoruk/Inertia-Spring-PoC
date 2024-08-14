# Inertia - Spring Boot

This is a minimal implementation of the [Inertia protocol](https://inertiajs.com/the-protocol) in Spring Boot for building full-stack applications without explicitly defining an API between the backend and the SPA frontend. Also includes a sample app using Inertia for displaying counters with their state stored server-side inside an in-memory DB.

I'm not planing to build this into a complete or production ready library (skill issue), so feel free to fork/copy it. Protocol is actually
simple, so completing the implementation shouldn't be hard either.

## Example

```java
// Controller.java
@GetMapping("counter/{id}")
public void renderCounter(@PathVariable("id") Integer id) throws IOException {
	var counter = repository.findById(id).orElseThrow();
	Inertia.render("Counter", counter);
}

@PostMapping("counter/{id}")
public void increaseCounter(@PathVariable("id") Integer id) throws IOException {
	var counter = repository.findById(id).orElseThrow();
	counter.setValue(counter.getValue() + 1);
	repository.save(counter);
	Inertia.render("Counter", counter);
}
```

```jsx
// Counter.jsx
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
