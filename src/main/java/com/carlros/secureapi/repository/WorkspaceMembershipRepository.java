package com.carlros.secureapi.repository;

import com.carlros.secureapi.model.WorkspaceMembership;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WorkspaceMembershipRepository extends JpaRepository<WorkspaceMembership, Long> {
    Optional<WorkspaceMembership> findByWorkspaceIdAndUserId(Long workspaceId, Long userId);
    List<WorkspaceMembership> findAllByUserId(Long userId);
    List<WorkspaceMembership> findAllByWorkspaceId(Long workspaceId);
}
