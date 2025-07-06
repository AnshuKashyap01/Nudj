package com.tpc.nudj.utils

import com.tpc.nudj.model.ClubUser
import com.tpc.nudj.model.NormalUser
import com.tpc.nudj.model.enums.Branch
import com.tpc.nudj.model.enums.ClubCategory
import com.tpc.nudj.model.enums.Gender


object FirestoreUtils {

    inline fun <reified T : Enum<T>> enumValueOrDefault(value: String?, defaultValue: T): T {
        return try {
            if (value == null) defaultValue else enumValueOf<T>(value)
        } catch (e: Exception) {
            defaultValue
        }
    }


    fun getEnumDisplayName(enum: Any?): String {
        return when(enum) {
            is Gender -> enum.genderName
            is Branch -> enum.branchName
            is ClubCategory -> enum.categoryName
            else -> enum?.toString() ?: ""
        }
    }

    fun toMap(clubUser: ClubUser): Map<String, Any?> {
        return mapOf(
            "clubId" to clubUser.clubId,
            "clubName" to clubUser.clubName,
            "description" to clubUser.description,
            "achievements" to clubUser.achievements,
            "clubEmail" to clubUser.clubEmail,
            "clubLogo" to clubUser.clubLogo,
            "clubCategory" to clubUser.clubCategory.name, // Store enum as string
            "clubCategoryName" to getEnumDisplayName(clubUser.clubCategory), // Store human-readable name
            "additionalDetails" to clubUser.additionalDetails
        )
    }

    fun toMap(normalUser: NormalUser): Map<String, Any?> {
        return mapOf(
            "userid" to normalUser.userid,
            "name" to normalUser.name,
            "firstName" to normalUser.firstName,
            "lastname" to normalUser.lastname,
            "email" to normalUser.email,
            "gender" to normalUser.gender.name, // Store enum as string
            "genderName" to getEnumDisplayName(normalUser.gender), // Store human-readable name
            "branch" to normalUser.branch.name, // Store enum as string
            "branchName" to getEnumDisplayName(normalUser.branch), // Store human-readable name
            "batch" to normalUser.batch,
            "profilePictureUrl" to normalUser.profilePictureUrl,
            "bio" to normalUser.bio
        )
    }


    fun toClubUser(data: Map<String, Any?>): ClubUser {
        return ClubUser(
            clubId = (data["clubId"] as? String) ?: "",
            clubName = (data["clubName"] as? String) ?: "",
            description = (data["description"] as? String) ?: "",
            achievements = (data["achievements"] as? String) ?: "",
            clubEmail = (data["clubEmail"] as? String) ?: "",
            clubLogo = data["clubLogo"] as? String,
            clubCategory = enumValueOrDefault(data["clubCategory"] as? String, ClubCategory.MISCELLANEOUS),
            additionalDetails = (data["additionalDetails"] as? String) ?: ""
        )
    }


    fun toNormalUser(data: Map<String, Any?>): NormalUser {
        return NormalUser(
            userid = (data["userid"] as? String) ?: "",
            name = (data["name"] as? String) ?: "",
            firstName = (data["firstName"] as? String) ?: "",
            lastname = (data["lastname"] as? String) ?: "",
            email = (data["email"] as? String) ?: "",
            gender = enumValueOrDefault(data["gender"] as? String, Gender.PREFER_NOT_TO_DISCLOSE),
            branch = enumValueOrDefault(data["branch"] as? String, Branch.CSE),
            batch = (data["batch"] as? Long)?.toInt() ?: 2024,
            profilePictureUrl = data["profilePictureUrl"] as? String,
            bio = (data["bio"] as? String) ?: ""
        )
    }

}
