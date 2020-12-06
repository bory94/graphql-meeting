# graphql-meeting
GraphQL Endpoint for Meetings

## Build
`gradle clean build`

## Run
`gradle bootRun`

## Demo
1. Go to http://localhost:8080/graphiql
2. Explore Schema
3. Try example queries

### Example Queries

```
query {
    person(id:1) {
        id
        firstName
        lastName
        email
        officePhoneNumber
        mobilePhoneNumber
    }
}


```

## Youtube Walkthrough
