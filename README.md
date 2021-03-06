# WhipLink
Totally original URL Shortening Service written in Kotlin  
https://whiplink.xyz/

Creates short links in the format whi.pw/XXXXXX that redirect to a predetermined destination

Example url  
[http://www.whi.pw/DjF6AD](http://www.whi.pw/DjF6AD) -> youtube.com

### Tech Stack
- [Svelte](https://svelte.dev/) - UI framework
- [Netlify](https://www.netlify.com/) - UI deployment and serverless functions for CORS proxy
- [Ktor](https://ktor.io/) - Kotlin framework
- [Heroku](https://heroku.com//) - Ktor server deployment
- [MongoDB Atlas](https://www.mongodb.com/cloud/atlas) - NoSQL database

## Architecure
![Architecture Diagram](https://raw.githubusercontent.com/GriffinOSI/ShortURL/master/Architecture.png?token=ACTUTB2EE5SVFQEZ2CJEGQC7VDEWY)

## TODO
- Allow custom short link values
- Copy to clipboard
- Strict validation
- Dynamic length to handle (unlikely) collisions
- UI tests
- UI copy to clipboard
- Save created URLs to session
- Integration tests
- Get mocks working
