package com.easyhz.patchnote.data.repository.team

import com.easyhz.patchnote.core.model.team.Team
import com.easyhz.patchnote.data.datasource.local.user.UserLocalDataSource
import com.easyhz.patchnote.data.datasource.remote.team.TeamRemoteDateSource
import com.easyhz.patchnote.data.mapper.team.toModel
import com.easyhz.patchnote.data.mapper.team.toRequest
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TeamRepositoryImpl @Inject constructor(
    private val teamRemoteDateSource: TeamRemoteDateSource,
    private val userLocalDataSource: UserLocalDataSource,
) : TeamRepository {
    override suspend fun findTeamByCode(inviteCode: String): Result<Team> =
        teamRemoteDateSource.findTeamByCode(inviteCode).map { it.toModel() }

    override suspend fun createTeam(team: Team): Result<Unit> =
        teamRemoteDateSource.createTeam(team.toRequest())

    override suspend fun findTeamById(teamId: String): Result<Team> =
        teamRemoteDateSource.findTeamById(teamId).map { it.toModel() }

    override suspend fun getTeamName(): Flow<String> =
        userLocalDataSource.getTeamName()
}