CREATE TABLE BBS (
	seq INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
	category VARCHAR(255) NOT NULL,
	user_id VARCHAR(255) NOT NULL,
	title VARCHAR(255) NOT NULL,
	content LONGTEXT NOT NULL,
	status VARCHAR(255) NOT NULL,
	count INT(11) UNSIGNED NOT NULL,
	create_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	ancestor INT(11) UNSIGNED NOT NULL,
	step INT(11) UNSIGNED NOT NULL,
	depth INT(11) UNSIGNED NOT NULL,
	PRIMARY KEY(seq)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

SELECT * FROM BBS WHERE category = 'notice' ORDER BY ancestor desc,reply asc

/*
 * 
 * 4	: int
 * 12	: varchar
 * -1	: longtext
 * 93	: timestamp
 * */
drop table bbs;

delete from bbs;

SELECT COUNT(SEQ) FROM BBS

SELECT seq,category,user_id,title,content,status,count,create_at,updated_at,ancestor,reply 
FROM BBS 
WHERE category = 'notice'
AND user_id LIKE '%no%'
ORDER BY ancestor desc,reply asc
limit 0, 10

(select * from (select ifnull(max(seq),0)+1 from BBS) as a)

SELECT seq,category,user_id,title,content,status,count,create_at,updated_at,ancestor,reply FROM BBS  WHERE category = 'notice'  AND title LIKE '%%' ORDER BY ancestor desc,reply asc  limit 0,10

UPDATE BBS SET TITLE = '안녕',  CONTENT = '방가'  WHERE SEQ = 3

SELECT * FROM BBS
WHERE REPLY IS NULL
WHERE ANCESTOR = 1;




SELECT REPLY FROM BBS 
WHERE ANCESTOR = 1
AND (
	SELECT ifnull(REPLY,0)+1 FROM BBS
	WHERE SEQ = 1
) = LENGTH(REPLY)
SELECT ifnull(REPLY,0) FROM BBS
WHERE SEQ = 1;

SELECT count(seq) FROM BBS 
WHERE ANCESTOR = 1
AND (
	SELECT ifnull(REPLY,0)+1 FROM BBS
	WHERE SEQ = 1
) = LENGTH(REPLY)

(SELECT CONCAT(ifnull(REPLY,''),'%') FROM BBS	WHERE SEQ = 5)

--자기자신이 포함 안되는 것이 문제
SELECT * FROM BBS 
WHERE REPLY LIKE (SELECT CONCAT(ifnull(REPLY,''),'%') FROM BBS	WHERE SEQ = 20)		
OR REPLY IS NULL																	
AND ANCESTOR = (SELECT ANCESTOR FROM BBS WHERE SEQ = 20)
AND length(reply) <= (SELECT ifnull(length(REPLY),0)+1 FROM BBS WHERE SEQ = 20)
ORDER BY reply asc

--자기자신이 포함 안되는 것이 문제
SELECT * FROM BBS 
WHERE REPLY LIKE (SELECT CONCAT(ifnull(REPLY,''),'%') FROM BBS	WHERE SEQ = 20)		
OR REPLY IS NULL																	
AND ANCESTOR = 20
AND length(reply) <= 1
ORDER BY reply asc
--NULL을 제외시키는 코드
--NULL 포함
SELECT * FROM (
	SELECT * FROM BBS
	WHERE REPLY IS NULL
	AND ANCESTOR = (SELECT ANCESTOR FROM BBS WHERE SEQ = 20)
)

/**
 * 1. 같은 조상을 가진 글들을 취합
 * 2. 같은 단계의 글만 포함
 * 3. NULL값 원문일 경우 NULL을 포함
 * 
 * */

-- 1단계
SELECT * FROM BBS
WHERE ANCESTOR = (SELECT ANCESTOR FROM BBS WHERE SEQ = 20)

-- 2단계
SELECT * FROM BBS
WHERE ANCESTOR = (SELECT ANCESTOR FROM BBS WHERE SEQ = 20)
AND length(reply) <= (SELECT ifnull(length(REPLY),0)+1 FROM BBS WHERE SEQ = 20)

-- 3단계
SELECT * FROM BBS
WHERE ANCESTOR = (SELECT ANCESTOR FROM BBS WHERE SEQ = 20)
AND length(reply) <= (SELECT ifnull(length(REPLY),0)+1 FROM BBS WHERE SEQ = 20)
OR REPLY IS NULL
AND ANCESTOR = (SELECT ANCESTOR FROM BBS WHERE SEQ = 20)

 INSERT INTO BBS(category,user_id,title,content,status,count,ancestor,reply)  VALUES( ?,?,?,?,?,0,( SELECT * FROM ( SELECT ancestor FROM BBS WHERE SEQ = 20 ) AS A),65) 
 
  INSERT INTO BBS(category,user_id,title,content,status,count,ancestor,reply)  
  VALUES( 'notice','123@gmail.com','re:테스트','ㅅㄷㄴㅅ','published',0,( SELECT * FROM ( SELECT ancestor FROM BBS WHERE SEQ = 28 )as sub),'A') 
  
  INSERT INTO BBS(category,user_id,title,content,status,count,ancestor,reply)  
  VALUES( 'general','notice','123@gmail.com','ㅅㄷㄴ','ㅅㄷㄴ',0,( SELECT * FROM ( SELECT ancestor FROM BBS WHERE SEQ = 27 ) AS sub),'AA') 
