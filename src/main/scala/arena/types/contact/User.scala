package arena.types.contact

import arena.types.foundation.Gender.Gender

/**
 * Created by bbhudia on 18/11/2015.
 */
case class User(firstName: String, middleName: String, lastName: String, gender: Gender, isVerified: Boolean, userImage: Array[Byte]) {
}
