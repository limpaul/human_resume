package com.example.recruit.mapper;

import com.example.recruit.domain.Bbs;
import com.example.recruit.dto.BbsContentDto;
import com.example.recruit.dto.BbsListDto;
import com.example.recruit.dto.BbsListInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BbsMapper {

    @Insert("INSERT INTO bbs(bbs_id, bbs_writer_id, bbs_writer, bbs_title, bbs_content, bbs_pw)" +
            "VALUES(#{bbs.bbsId}, #{bbs.bbsWriterId},#{bbs.bbsWriter}, #{bbs.bbsTitle}, #{bbs.bbsContent}, #{bbs.bbsPw})")
    int writeBoard(@Param("bbs")Bbs bbs);


    @Select("SELECT b.bbs_id, b.bbs_title, b.bbs_like, b.bbs_read ,b.bbs_writer, b.bbs_date\n" +
            "FROM bbs b order by bbs_id desc limit #{page}, 5")
    List<BbsListDto> listBoard(@Param("page") int page); /* 게시판 목록 */

    @Select("SELECT CEIL(COUNT(*) / 5) FROM bbs")
    int bbsPage();

    @Select("SELECT bbs_id, bbs_title, bbs_content, bbs_like , bbs_writer\n" +
            "FROM bbs " +
            "where bbs_id = #{bbsId}")
    BbsContentDto findBbsContentById(@Param("bbsId") int bbsId);/* 게시판 상세 목록 */


    @Select("SELECT bbs_pw = #{bbsPw} FROM bbs where bbs_id = #{bbsId}")
    Boolean checkBbsPassword(@Param("bbsId") int bbsId, @Param("bbsPw") String bbsPw);


    @Select("SELECT bbs_id, bbs_title, bbs_writer, bbs_date, bbs_read" +
            " FROM bbs WHERE bbs_title like \"%\"#{title}\"%\" " +
            "order by bbs_id desc ")
    List<BbsListDto> searchByTitleBbsList(@Param("title") String title);

    @Select("SELECT bbs_id, bbs_title, bbs_writer, bbs_date, bbs_read" +
            " FROM bbs where bbs_content like  \"%\"#{content}\"%\" " +
            "order by bbs_id desc")
    List<BbsListDto> searchByContentBbsList(@Param("content") String content);

    @Select("select bbs_id, bbs_title, bbs_writer, bbs_date, bbs_read from bbs\n" +
            "where bbs_title like \"%\"#{titleAndContent}\"%\" or bbs_content like \"%\"#{titleAndContent}\"%\" " +
            "order by bbs_id desc")
    List<BbsListDto> searchByTitleAndContentBbsList(@Param("titleAndContent")String titleAndContent);


    @Select("SELECT CEIL(COUNT(*) / 5) FROM bbs " +
            "where bbs_title like  \"%\"#{data}\"%\" or bbs_content like  \"%\"#{data}\"%\" order by bbs_id desc")
    int searchCount(String data);

    @Delete("DELETE FROM bbs where bbs_id = #{bbsId}")
    int deleteBbs(@Param("bbsId") int bbsId);

    @Update("UPDATE bbs SET bbs_title = #{bbs.bbsTitle}, bbs_content = #{bbs.bbsContent}, bbs_pw = #{bbs.bbsPw}" +
            "where bbs_id = #{bbs.bbsId}")
    int editBoard(@Param("bbs") Bbs bbs);



}
