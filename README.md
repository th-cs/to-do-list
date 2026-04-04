# To-Do List API

## Endpoints

- User endpoints

<table>
    <thead>
        <tr>
            <th scope="col">Description</th>
            <th scope="col">Method</th>
            <th scope="col">Endpoint</th>
            <th scope="col">Request Body</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>List all existing users</td>
            <td><code>GET</code></td>
            <td><code>/api/users</code></td>
            <td></td>
        </tr>
        <tr>
            <td>List user by ID</td>
            <td><code>GET</code></td>
            <td><code>/api/users/{userId}</code></td>
            <td></td>
        </tr>
        <tr>
            <td>List tasks by User</td>
            <td><code>GET</code></td>
            <td><code>/api/users/{userId}/tasks</code></td>
            <td></td>
        </tr>
        <tr>
            <td>Create new user</td>
            <td><code>POST</code></td>
            <td><code>/api/users</code></td>
<td>

```json
{
  "name": "{String}",
  "email": "{String}"
}
```

</td>
        </tr>
        <tr>
            <td>Create new task</td>
            <td><code>POST</code></td>
            <td><code>/api/users/{userId}/tasks</code></td>
<td>

```json
{
  "title": "{String}",
  "description": "{String}",
  "isDone": "{Boolean}"
}
```

</td>
        </tr>
        <tr>
            <td>Update user</td>
            <td><code>PUT</code></td>
            <td><code>/api/users/{userId}</code></td>
<td>

```json
{
  "name": "{String}",
  "email": "{String}"
}
```

</td>
        </tr>
        <tr>
            <td>Delete user</td>
            <td><code>DELETE</code></td>
            <td><code>/api/users/{userId}</code></td>
            <td></td>
        </tr>
    </tbody>
</table>

- Task endpoints

<table>
    <thead>
        <tr>
            <th scope="col">Description</th>
            <th scope="col">Method</th>
            <th scope="col">Endpoint</th>
            <th scope="col">Request Body</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>List all existing tasks</td>
            <td><code>GET</code></td>
            <td><code>/api/tasks</code></td>
            <td></td>
        </tr>
        <tr>
            <td>List task by ID</td>
            <td><code>GET</code></td>
            <td><code>/api/tasks/{taskId}</code></td>
            <td></td>
        </tr>
        <tr>
            <td>Update task</td>
            <td><code>PUT</code></td>
            <td><code>/api/tasks/{taskId}</code></td>
<td>

```json
{
  "title": "{String}",
  "description": "{String}",
  "isDone": "{Boolean}"
}
```

</td>
        </tr>
        <tr>
            <td>Delete task</td>
            <td><code>DELETE</code></td>
            <td><code>/api/tasks/{taskId}</code></td>
            <td></td>
        </tr>
    </tbody>
</table>

## Screenshots


## 1:N Relationship

In a 1:N relationship, an entity A may be related to zero, one or multiple rows
of an entity B, while each of these rows of B is related to a single row of A.

A user (A) may have zero, one or multiple tasks (B), but each task belongs to
only one user.
