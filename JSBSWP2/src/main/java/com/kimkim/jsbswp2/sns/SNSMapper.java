package com.kimkim.jsbswp2.sns;

import java.util.List;

import com.kimkim.jsbswp2.member.Member;

public interface SNSMapper {
	public abstract int writeMsg(SNSMsg sm);
	public abstract int writeReply(SNSReply sr);
	public abstract Integer getMsgCount(SNSSelector sSel);
	public abstract Integer getMsgCountByOwner(Member m);
	public abstract List<SNSMsg> getMsg(SNSSelector sSel);
	public abstract List<SNSReply> getReply(SNSMsg sm);
	public abstract int updateMsg(SNSMsg sm);
	public abstract int deleteMsg(SNSMsg sm);
	public abstract int deleteReply(SNSReply sr);
}
