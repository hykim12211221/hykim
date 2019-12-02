package com.kimkim.jsbswp2.member;

public interface MemberMapper {
	public abstract int join(Member m);
	public abstract Member getMemberByID(Member m); // 어쩌피 하나만 나와서 이번엔 Member로 받음(보통은 List타입으로)
	public abstract int update(Member m);
	public abstract int bye(Member m);
}
