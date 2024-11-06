package ru.connect.profile.create

sealed interface ProfileCreateNavigationTarget {
    data object Back : ProfileCreateNavigationTarget
}
