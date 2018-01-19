# _Blog_

#### _An application program that allows a users to contribute their thoughts and ideas to the existing blog._

#### By _**Kate Trahan**_

## Description

_This is an application that uses Java to allow a user to create a blog. The application was started last week in class and I have added DAO and SQL onto this project. The application will also allow users to add information about the author along with the project. Created 1/12/2018._


### Specs
| Behavior | Input | Output |
| :-------------     | :------------- | :-------------
| **Accept 1 blog post**| "Blog Post" | "Blog Post" |
| **Accept 1 author of blog post and return 1 author of blog post**| "Author" | "Author" |
| **Accept author of blog ** |"Oprah"|"Author:Oprah"|
| **Accept 2 blog posts and return 2 blog posts**| "Blog Post One, Blog Post Two" | "Blog Post One, Blog Post Two" |
| **Accepts 2 blog posts by the same author and returns two blog posts by one author** |"Post One, Post Two"|"Post One, Post Two: By Oprah"|
| **Accepts 2 authors and returns 2 authors**| "author one, author two" | "author one, author two" |
| **Accepts 3 blog posts and return 3 blog posts**| "Post 1, Post 2, Post 3"|"Post 1, Post 2, Post 3"|
| **Returns Correct Number of Entries** | "Post, Author"| "2"|
| **Find ID of post and return correct ID**| "New Post"| "1"|
| **Find correct author when more than one author exists**| "New Author, Oprah"| "2"|
| **Edits blog post and return new blog post **| "New Post"|"New Post"|



## Setup/Installation Requirements

* _Clone repository to your machine from GitHub_
* _Open cloned repository in IntelliJ_

## Known Bugs

_No known issues._

## Support and contact details

_If you have any issues or have questions, ideas or concerns please email kathrynceciliatrahan@gmail.com or contribute to the code_

## Technologies Used

* _Java_
* _IntelliJ_
* _JUnit_
* _Spark_


### License
Copyright (C) 2017 Kate Trahan

This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.

You should have received a copy of the GNU General Public License along with this program. If not, see https://www.gnu.org/licenses/.
