package bimframework.collabor.project.base.dao;

import java.sql.Date;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bim.common.exception.ServiceRuleException;

import bimframework.collabor.project.dao.entity.folder.FolderRoleEntity;
import bimframework.collabor.project.dao.entity.project.TeamUserEntity;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class BatchRepository {
    private final JdbcTemplate jdbcTemplate;

    public int batchInsertTeamUser(List<TeamUserEntity> list)throws ServiceRuleException{
        return jdbcTemplate.batchUpdate("INSERT INTO manage.tb_team_user(tenant_id, prj_id, team_id, user_id, user_nm, prj_auth, use_yn, crt_dt, crt_user_id, updt_dt, updt_user_id, crt_ip_adres, updt_ip_adres)"+
            "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
            list,list.size(),
            (ps,arg)->{
                ps.setString(1, arg.getTenantId());
                ps.setString(2, arg.getProjectId());
                ps.setString(3, arg.getTeamId());
                ps.setString(4, arg.getUserId());
                ps.setString(5, arg.getUserName());
                ps.setString(6, arg.getProjectAuthority());
                ps.setString(7, arg.getUseYn());
                ps.setDate(8, new Date(arg.getCreateDateTime().getTime()));
                ps.setString(9, arg.getCreateUserId());
                ps.setDate(10, null);
                ps.setString(11, arg.getUpdateUserId());
                ps.setString(12, arg.getCreateIp());
                ps.setString(13, arg.getUpdateIp());
        }).length;
    }

    public int batchInsertFolderRole(List<FolderRoleEntity> list)throws ServiceRuleException{
        return jdbcTemplate.batchUpdate("INSERT INTO manage.tb_folder_role (fldr_id, user_id, role_cd, use_yn, crt_dt, crt_user_id, updt_dt, updt_user_id, crt_ip_adres, updt_ip_adres, tenant_id) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
            list,list.size(),
            (ps,arg)->{
                ps.setString(1, arg.getFolderId());
                ps.setString(2, arg.getUserId());
                ps.setString(3, arg.getRoleCode());
                ps.setString(4, arg.getUseYn());
                ps.setDate(5, new Date(arg.getCreateDateTime().getTime()));
                ps.setString(6, arg.getCreateUserId());
                ps.setDate(7, null);
                ps.setString(8, arg.getUpdateUserId());
                ps.setString(9, arg.getCreateIp());
                ps.setString(10, arg.getUpdateIp());
                ps.setString(11, arg.getTenantId());
            }
        ).length;
    }



}
