# Todo Lib
A shared library for the to-do list project

## Add dependency in Gradle
Add the following to `repositories`

```groovy
maven {
    url("https://git.uwaterloo.ca/api/v4/projects/69446/packages/maven")
    name "GitLab"
    credentials(HttpHeaderCredentials) {
        name = 'Deploy-Token'
        value = 'KtqpZE3e2fURjpqgd7BG'
    }
    authentication {
        header(HttpHeaderAuthentication)
    }
}
```
and add the following to `dependencies`
```groovy
implementation "edu.uwaterloo.cs:todo-lib:1.0.0.31-DEV"
```
