package com.carlros.secureapi.service;

import com.carlros.secureapi.exception.EntityNotFoundException;
import com.carlros.secureapi.model.User;
import com.carlros.secureapi.model.Workspace;
import com.carlros.secureapi.model.WorkspaceMembership;
import com.carlros.secureapi.repository.WorkspaceMembershipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WorkspaceMembershipService {
    private final WorkspaceMembershipRepository membershipRepository;

    public WorkspaceMembership one(Long id) {
        return membershipRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(String.format("Membership with id %s does not exist.", id)));
    }

    public List<WorkspaceMembership> all() {
        return membershipRepository.findAll();
    }

    public List<WorkspaceMembership> findAllByUserId(Long userId) {
        return membershipRepository.findAllByUserId(userId);
    }

    public List<WorkspaceMembership> findAllByWorkspaceId(Long userId) {
        return membershipRepository.findAllByWorkspaceId(userId);
    }

    public List<Workspace> userWorkspaces(Long userId) {
        return findAllByUserId(userId).stream()
                .map(WorkspaceMembership::getWorkspace)
                .collect(Collectors.toList());
    }

    public List<User> workspaceMembers(Long userId) {
        return findAllByWorkspaceId(userId).stream()
                .map(WorkspaceMembership::getUser)
                .collect(Collectors.toList());
    }




    private Optional<WorkspaceMembership> findByWorkspaceIdAndUserId(Long workspaceId, Long userId) {
        return membershipRepository.findByWorkspaceIdAndUserId(workspaceId, userId);
    }


    public void delete(Long id) {
        membershipRepository.deleteById(id);
    }

    private boolean isUnique(WorkspaceMembership membership) {

        return  membership.getWorkspace() == null
                || membership.getUser() == null
                || findByWorkspaceIdAndUserId(membership.getWorkspace().getId(), membership.getUser().getId()).isEmpty();
    }

    public void addMember(Workspace workspace, User user){
        WorkspaceMembership membership = new WorkspaceMembership();
        membership.setWorkspace(workspace);
        membership.setUser(user);

        if(isUnique(membership)) {
            membershipRepository.save(membership);
        }
    }
}
