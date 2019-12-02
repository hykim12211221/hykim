-----------------------------------------
select count(*) 
from jsbswp_sns, jsbswp_member
where js_owner = jm_id and js_txt like '%��%';

select *
from (
	select rownum as rn, js_no, js_owner, js_txt, js_when, jm_photo
	from (
		select * 
		from jsbswp_sns, jsbswp_member
		where js_owner = jm_id and js_txt like '%%'
		order by js_when desc
	)
)
where rn >= 1 and rn <= 2;

create table jsbswp_sns(
	js_no number(5) primary key, 			--��ȣ(AI)
	js_owner varchar2(10 char) not null,	--�۾����ID
	js_txt varchar2(300 char) not null, 	--�۳���
	js_when date not null					--�� ��¥
);
create sequence jsbswp_sns_seq;

create sequence jsbswp_sns_reply_seq;

insert into jsbswp_sns values(jsbswp_sns_seq.nextval, 'test', '�׽�Ʈ', sysdate);

select * from jsbswp_sns;

select * from jsbswp_sns_reply;

delete from jsbswp_member;
-----------------------------------------
update JSBSWP_MEMBER
set jm_pw=?, jm_name=?, jm_addr=?, jm_photo=?
where jm_id=?

create table jsbswp2_member(
	jm_id varchar2(10 char) primary key,
	jm_pw varchar2(10 char) not null,
	jm_name varchar2(10 char) not null,
	jm_addr varchar2(100 char) not null,
	jm_photo varchar2(100 char) not null
);

select * from jsbswp2_member;

drop table jsbswp_member;

delete from jsbswp_sns where js_no = 2;

-- ȸ���� Ż���ߴٸ� -> �۵� ����
-- foreignkey
-- jm_id�� ���� ���� ���� ����
alter table ���̺��
add constraint �������Ǹ�(����ڰ� ����)
foreign key �ʵ��
references ���̺��(�ʵ��)
on delete cascade;

alter table jsbswp_sns
add constraint jsbswp_sns_owner
foreign key(js_owner)
references jsbswp_member(jm_id)
on delete cascade;

insert into jsbswp_sns values(1000, '1111', test, sysdate);

create table jsbswp_sns_reply(
	jsr_no number(5) primary key,  -- ��۹�ȣ
	jsr_js_no number(5) not null,	 -- �Ҽӵ� �� ��ȣ
	jsr_owner varchar2(10 char) not null,
	jsr_txt varchar2(100 char) not null,
	jsr_when date not null,
	-- reply�� no�� sns�� no�� �����ϰ� sns�� ���� �������� �ش� reply ���� ���� ����
	constraint js_jsr foreign key(jsr_no) references jsbswp_sns(js_no) on delete cascade
);

insert into jsbswp_sns_reply values(jsbswp_sns_reply_seq.nextval, )

delete from jsbswp_sns_reply where jsr_no = 2;

select count(*) from jsbswp_sns, jsbswp_member
		where js_owner = jm_id;
		
select *
		from (
			select rownum as rn, js_no, js_owner, js_txt, js_when, jm_photo
			from (
					select *
					from jsbswp_sns, jsbswp_member
					where js_owner = jm_id and js_txt like '%%'
					order by js_when desc
					)
			)
	where rn >= 1 and rn <= 2;
		
select *
from (
	select rownum as rn, js_no, js_owner, js_txt, js_when, jm_photo
	from (
		select * 
		from jsbswp_sns, jsbswp_member
		where js_owner = jm_id and js_txt like '%%'
		order by js_when desc
	)
)
where rn >= 1 and rn <= 2;

update JSBSWP_SNS
set js_txt=#{js_txt}
where js_no=#{js_no};
