<div align="center">
 <img
  width="450"
 alt=".dotfiles"
 src="https://i.imgur.com/I3B2YXo.png">
<br>
<br>

<h4>
  🚀 Simple book-swap application
</h4>

<a href="#ℹ️-about">ℹ️ About</a> •
<a href="#-how-to-start"> 🏁 How to start</a> •
<a href="#-technologies">⚒️ Technologies</a> •
<a href="#-features">📋 Features</a>

</div>

---

## ℹ️ About

BookSwapHub is an online platform that unites book enthusiasts for exchanging physical or digital books. With an engaging interface, it encourages reading and collaboration through book searches, swap requests, and a community space for sharing insights and reviews. BookSwapHub turns discovering your next favorite read into a social adventure.

## 🏁 How To Start

#### Windows
1. Install Java: 
	- Click this [link](https://jdk.java.net/19/ "link") to download Java.
	- Set environment variable Java Home.

2. Install Maven: 
	- Click this [link](https://maven.apache.org/download.cgi") to download Maven.
	- Set environment variable M2 Home.

3. Clone this repository: `git clone https://github.com/plexus-tech/book-swap-hub.git`.

4. Clean and Package the application without tests, with maven: `mvn clean package -DskipTest`
5. Create a docker image: 
	- Go to the root folder to run the Dockerfile.
	- Build Docker image with: `docker build -t target-image .`
6. Run the application: 
	- Go to Docker folder to run the docker-compose.yml: `cd docker`.
	- 🚀Run the app with: `docker-compose up`

#### Linux
1. Install Java: `sudo apt install openjdk-19-jdk`

2. Install Maven:  `sudo apt install maven`

3. Clone this repository: `git clone https://github.com/plexus-tech/book-swap-hub.git`.

4. Clean and Package the application without tests, with maven: `mvn clean package -DskipTest`
5. Create a docker image: 
	- Go to the root folder to run the Dockerfile.
	- Build Docker image with: `docker build -t target-image .`
6. Run the application: 
	- Go to Docker folder to run the docker-compose.yml: `cd docker`.
	- 🚀Run the app with: `docker-compose up`

## ⚒ Technologies

- ![IntelliJ IDEA](https://img.shields.io/badge/IntelliJIDEA-000000.svg?&logo=intellij-idea&logoColor=white)
- ![Java](https://img.shields.io/badge/java-%23ED8B00.svg?&logo=openjdk&logoColor=white)
- ![Spring-boot](https://img.shields.io/badge/springBoot-%236DB33F.svg?logo=spring&logoColor=white)
- ![Apache Maven](https://img.shields.io/badge/Apache%20Maven-C71A36?&logo=Apache%20Maven&logoColor=white)
- ![MariaDB](https://img.shields.io/badge/MariaDB-003545?&logo=mariadb&logoColor=white)
- ![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?&logo=docker&logoColor=white)
- ![JWT](https://img.shields.io/badge/JWT-black?&logo=JSON%20web%20tokens)
- ![Hibernate](https://img.shields.io/badge/Hibernate-59666C?&logo=Hibernate&logoColor=white)
- ![Git-flow](https://img.shields.io/badge/gitFlow-%23F05033.svg?&logo=git&logoColor=white)
- ![GitHub](https://img.shields.io/badge/github-%23121011.svg?&logo=github&logoColor=white)
- ![GitHub Actions](https://img.shields.io/badge/github%20actions-%232671E5.svg?&logo=githubactions&logoColor=white)
- ![Swagger](https://img.shields.io/badge/-Swagger-%23Clojure?&logo=swagger&logoColor=white)
## 📋 Features

###  🌚 ACTIONS

###### USER

- You can register users.

- You can login users.

- You can delete users.

- You can update users.

- You can get users details.

###### BOOK

- You can register books.

- You can delete books.

- You can update books.

- You can get books details.

###### SWAP

- You can create a swap with other user and with many books.

- You can get swaps details.

- You can accept or reject swaps.

### 🤝 Contributors
[![Cristian Santiago Sanchez](https://avatars.githubusercontent.com/u/73582495?v=4 "Cristian Santiago Sanchez")](https://github.com/CristianSantiagoSanchez "Cristian Santiago Sanchez")
<div>
<a  href="https://github.com/regadior" > 
<img
  width="130"
 alt=".dotfiles"
 src="https://avatars.githubusercontent.com/u/83121658?v=4">
 </a>
</div>


### ⭐ Stargazers

[![Stargazers repo roster for @plexus-tech/book-swap-hub](https://reporoster.com/stars/plexus-tech/book-swap-hub)](https://github.com/plexus-tech/book-swap-hub/stargazers)
