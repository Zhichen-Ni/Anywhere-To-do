# ToDo_WebService
A Spring-boot based application handling RESTful APIs

## Tech Stack
Material UI


## Useful Links
[Build Java apps with Microsoft Graph](https://learn.microsoft.com/en-us/graph/tutorials/java?tabs=aad)

[Get google user info](https://stackoverflow.com/questions/7130648/get-user-info-via-google-api)

## Google OAuth2 info
API key: AIzaSyDoPlpfcaCBQupieIRpeh7mxXM6YxgJaFE

Client Id: 248432300243-aldppbvkn85sbcurnfgl7ardbfon0ueu.apps.googleusercontent.com

Client secret: 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

Google OAuth endpoint: https://oauth2.googleapis.com/token

[Set up OAuth through POST request](https://developers.google.com/identity/protocols/oauth2/web-server#httprest_7)

[Get avaliable apis](https://console.cloud.google.com/apis/library/browse?project=anywhere-todo&q=people)

[Get scopes](https://developers.google.com/oauthplayground/)


## OAuth Request URL:

[Add redirect_url](https://console.cloud.google.com/apis/credentials?project=anywhere-todo)

window.location.href= 'https://accounts.google.com/o/oauth2/v2/auth?scope=https%3A//www.googleapis.com/auth/drive.metadata.readonly&access_type=offline&include_granted_scopes=true&response_type=code&redirect_uri=http://localhost:8887&client_id=248432300243-aldppbvkn85sbcurnfgl7ardbfon0ueu.apps.googleusercontent.com'


## Google Calendar:
[Add ToDo](https://developers.google.com/calendar/api/v3/reference/events/insert?apix_params=%7B%22calendarId%22%3A%22primary%22%2C%22resource%22%3A%7B%22end%22%3A%7B%22timeZone%22%3A%22America%2FToronto%22%2C%22dateTime%22%3A%222022-11-05T17%3A00%3A00%22%7D%2C%22start%22%3A%7B%22timeZone%22%3A%22America%2FToronto%22%2C%22dateTime%22%3A%222022-11-05T10%3A00%3A00%22%7D%2C%22summary%22%3A%2212314%22%2C%22location%22%3A%22800%20Howard%20St.%2C%20San%20Francisco%2C%20CA%2094103%22%2C%22description%22%3A%22A%20chance%20to%20hear%20more%20about%20Google%27s%20developer%20products.%22%7D%7D)


[List Calendar](https://developers.google.com/calendar/api/v3/reference/calendarList/list)

 
