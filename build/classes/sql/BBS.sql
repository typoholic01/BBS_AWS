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
	reply	VARCHAR(10),
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

SELECT seq,category,user_id,title,content,status,count,create_at,updated_at,ancestor,reply FROM BBS  WHERE category = 'notice'  AND &s_type=title LIKE %&q=Hi% ORDER BY ancestor desc,reply asc  limit 0,10

UPDATE BBS SET TITLE = '안녕',  CONTENT = '방가'  WHERE SEQ = 6