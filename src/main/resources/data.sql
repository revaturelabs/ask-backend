-- Mock data added 103019 by @author Efrain Vila

--data for the users table
INSERT INTO users VALUES
    (default,'James Bishop','1234',false), -- user login
    (default,'Sally OBrien','1234',false), -- user login 2
    (default,'Thomas Dawson','4321',true), -- expert login
    (default,'Chad Paulson','4321',true),  -- expert login 2
    (default,'Billy Carter','1234',false), -- user login 3
    (default,'Winifred Hurst','4321',true),  -- expert login 3
    (default,'Harry Brandt','1234',false), -- user login 4
    (default,'Aubrey Garner','4321',true);  -- expert login 4

-- data for tags table
INSERT INTO tags VALUES
    (DEFAULT, 'JavaScript'), --1
    (DEFAULT, 'SQL'),
    (DEFAULT, 'Java'),
    (DEFAULT, 'HTML'),
    (DEFAULT, 'Angular'), --5
    (DEFAULT, 'Bootstrap'),
    (DEFAULT, 'React'),
    (DEFAULT, 'Docker'),
    (DEFAULT, 'Spring'),
    (DEFAULT, 'PostgreSQL'), --10
    (DEFAULT, 'Kubernetes'),
    (DEFAULT, 'Algorithms'),
    (DEFAULT, 'Data Structures'),
    (DEFAULT, 'Optimization'),
    (DEFAULT, 'Architecture'), --15
    (DEFAULT, 'Design Patterns'),
    (DEFAULT, 'Spring Boot'),
    (DEFAULT, 'Spring Data'),
    (DEFAULT, 'Hibernate'),
    (DEFAULT, 'TypeScript'), --20
    (DEFAULT, 'Vue'),
    (DEFAULT, 'Unix'),
    (DEFAULT, 'AWS'),
    (DEFAULT, 'REST'),
    (DEFAULT, 'SOAP'), --25
    (DEFAULT, 'Microservices'),
    (DEFAULT, 'Python'),
	(DEFAULT, 'MongoDB'),
	(DEFAULT, 'C'),
	(DEFAULT, 'Angular Material'), --30
	(DEFAULT, 'CSS'),
	(DEFAULT, 'Other');

-- data for questions
INSERT INTO questions VALUES 
  (DEFAULT, 1, null, 'Java Collections',
  	'What are Collections in Java?',
  	'2015-12-17'),
  (DEFAULT, 2, null, 'Angular Structural vs. Attribute Directives',
  	'What is the difference between structural and attribute directives?',
  	'2015-12-17'),
  (DEFAULT, 1, null, 'Why use Java',
  	'I am wondering what are some advantages of using Java over other programming languages?',
  	'2015-12-17'),
  (DEFAULT, 2, null, 'Input type="submit" not working inside of div',
  	'I want the user to insert his name and when he submits function y() runs.All of this was working till I decided I wanted to put some style into the form, so I cointained everything inside the div with the class of "loginbox". When I did that the form got the style from the class loginbox but does not submit anything nor runs function y(). Hover effect also is not working. I want to understand why and fix this issue.',
  	'2015-12-17'),
  (DEFAULT, 1, null, 'Read / convert an InputStream to a String',
  	'If you have a java.io.InputStream object, how should you process that object and produce a String? Suppose I have an InputStream that contains text data, and I want to convert it to a String, so for example I can write that to a log file. What is the easiest way to take the InputStream and convert it to a String?',
  	'2015-12-17'),
  (DEFAULT, 2, null, 'HashMap vs. HashTable',
  	'I am not sure whether I should use HashMap or HashTable.  I have heard HashTable is synchronized, but I have also heard you need to write your own synchronization for both.  Can I get some expert advice?',
  	'2015-12-17'),
  (DEFAULT, 1, null, 'Speed up PostgreSQL queries',
  	'I am working with a Postgres database and some of our queries are really slow.  I know I should start by adding indices, but I also know each additional index makes writes slower.  How do I decide what to index, or should I be doing something else entirely?',
  	'2015-12-17'),
  (DEFAULT, 2, null, 'Primitives in TS vs. JS?',
  	'JS has 6 primitives (7 if you count bigint).  Does TS have the same?  I found nice documentation on primitive (non-object) types from MDN for JavaScript, but I could not find anything on primitives in TypeScript.  The TS docs do list data types, but I do not know which are primitives.  Please help!  Sources: https://developer.mozilla.org/en-US/docs/Glossary/Primitive and https://www.typescriptlang.org/docs/handbook/basic-types.html',
  	'2016-12-17'),
  (DEFAULT, 1, null, 'Playing snake on EC2',
  	'I found this awesome "nsnake" game that runs on Ubuntu, but my EC2 is running Amazon Linux 2.  How can I play snake without installing Ubuntu locally or spinning up an a different EC2?',
  	'2016-11-17'),
  (DEFAULT, 2, null, 'RESTful architecture in my situation?',
  	'I am helping to design a microservice for internal use at my company.  It will process and transfer large (>10mb) images between 2 other services.  I wanted to make our API RESTful, but one of my colleagues just wants to quickly set up endpoints for RPCs and call it a day.  I know making a RESTful API will be more work, but I think it will pay off in the future.  Can someone help me weigh the benefits and drawbacks of each approach?',
	'2016-11-18'),
  (DEFAULT, 1, null, '"Baking" images?',
  	'During training I became a Docker master and learned how to containerize my deployments, but my first day on the job everyone around me was talking about "baking" docker images.  I have no idea what they are talking about, and running "docker bake" just gives me an error -- please help!',
  	'2016-12-11'),
  (DEFAULT, 7, null, 'Can not interpolate variable into object in html',
  	'How can I add a variable to an object in the html? I am using ngx-countdown timer and I need to add the value of the json data into the [config] object, but I am having issues figuring out how. How can I interpolate or dynamically insert the value into the config object? Originally it looked like <countdown [config]={ leftTime: 120, format: m:s }>',
  	'2019-10-09'),
  (DEFAULT, 5, null, 'Using Fabric.js with Angular 4',
  	'I am new to Angular and Fabric.js. I am trying to build a UI where users can drag and drop objects onto a canvas and then connect them with lines. In essence I am hoping to get the Angular4 drag and drop example from the first link to play well with the Fabric.js canvas found at the second link. The drag and drop example works but the Fabric.js canvas renders in Chrome as a square box and nothing more.',
  	'2019-10-29'),
  (DEFAULT, 2, null, 'SimpleDateFormat parsing date with Z literal',
  	'I am trying to parse a date that looks like this:2010-04-05T17:16:00Z. This is a valid date per http://www.ietf.org/rfc/rfc3339.txt. The Z literal (quote) imply that UTC is the preferred reference point for the specified time. If I try to parse it using SimpleDateFormat and this pattern: yyyy-MM-dd-T-HH:mm:ss it will be parsed as a Mon Apr 05 17:16:00 EDT 2010 SimpleDateFormat is unable to parse the string with these patterns:',
  	'2019-10-03'),
  (DEFAULT, 5, null, 'How to calculate maximum/minimum number of probes required to build a hash-table',
  	'I am currently working with Algorithms Fourth Edition by Robert Sedgewick and Kevin Wayne and Im stuck on one of the exercises. I know that theres someone whos posted the solutions to many of the exercises from this book on GitHub, but I want to do them on my own so that I can understand and learn from them. I have been scanning the book for how to calculate the maximum/minimum number of probes required to build a hash-table (exercise 3.4.12), but I cant find any methods/functions/formulas which shows how to move forward when dealing with such a problem.',
  	'2019-10-03'),
  (DEFAULT, 1, null, 'Trying to center my div using CSS',
  	'I am trying to center my div element in the center of the screen. I can set it 35% of the way to the left, which looks centered sometimes, but when the screen size changes it gets ruined. There has to be a better way to do this.',
  	'2019-10-28');

-- data for responses
INSERT INTO responses VALUES 
  (DEFAULT, 3, 1,'Collections is a framework that is designed to store the objects and manipulate the design to store the objects.', '2019-10-29'),
  (DEFAULT, 4, 1,'Collections are any objects that implement the Interface Collection.', '2019-10-31'),
  (DEFAULT, 6, 1,'Collections is a utility class in Java to work with objects that implement the Interface Collection.', '2019-10-30'),
  (DEFAULT, 3, 5,'You can use a Scanner to make the conversion.  The scanner will by default split on whitespace and split your string up into tokens.  A neat workaround is to make your Scanner split on the character "//A".  This character marks the beginning of the stream, so the scanner will not split up your stream into tokens at all and myScanner.next() gives you the whole string.',
  	'2019-10-31'),
  (DEFAULT, 4, 6,'here are several differences between HashMap and Hashtable in Java:  Hashtable is synchronized, whereas HashMap is not. This makes HashMap better for non-threaded applications, as unsynchronized Objects typically perform better than synchronized ones. Hashtable does not allow null keys or values. HashMap allows one null key and any number of null values.  If synchronization is not an issue, use HashMap.  If synchronization is necessary, use HashMap and write your own synchronization logic.  HashTable should never be used.  ConcurrentHashMap might solve your problems if you want an out-of-the-box solution.',
	'2019-10-31'),
  (DEFAULT, 3, 16,'position: absolute; top: 50%; //*moves the element halfway DOWN the screen*// left: 50%; //*moves the element halfway ACROSS the screen, at this point the top left corner of the will be at the center of the screen, so we just need to move it half of its own height and half of its own width backwards*// transform: translate(-50%, -50%); //*moves it half of its own height and half of its own width backwards*//', 
    '2019-10-29');

-- data for questions_tags
-- this is a junction table for questions and tags
INSERT INTO questions_tags VALUES
    (1, 3),
    (2, 5),
    (3, 3),
    (4, 1),
    (4, 4),
    (5, 3),
    (6, 3),
    (6, 13),
    (7, 2),
    (7, 10),
    (7, 14),
    (8, 1),
    (8, 20),
    (9, 22),
    (9, 23),
    (10, 15),
    (10, 24),
    (10, 26),
    (11, 8),
    (12, 4),
    (13, 5),
    (14, 3),
    (15, 12),
    (16, 31);
 --   (DEFAULT, DEFAULT); -- if needed
 
-- data for user_tags
-- this a junction table for users and tags
 INSERT INTO users_tags VALUES
    (3, 1),
    (4, 2),
    (3, 3),
    (3, 5),
    (3, 15),
    (4, 3),
    (4, 5),
    (4, 16),
    (4, 28),
    (6, 3),
    (6, 5),
    (6, 22),
    (8, 3),
    (8, 5),
    (8, 17),
    (3, 31);
 --   (DEFAULT, DEFAULT); -- if needed