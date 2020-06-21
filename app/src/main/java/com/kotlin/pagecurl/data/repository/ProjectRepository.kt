package com.kotlin.pagecurl.data.repository

import com.kotlin.pagecurl.data.api.ProjectService
import javax.inject.Inject

class ProjectRepository @Inject constructor(private val projectService: ProjectService) {
    suspend fun fetchUserList(number: Int) = projectService.getUserList(number)
}
