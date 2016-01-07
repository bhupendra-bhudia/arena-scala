package arena.contact

import arena.foundation.Gender.Gender

/**
 * @author Bhupendra Bhudia <bhupendra.bhudia@quedex.co>
 *         18/11/2015 13:21
 */
case class User(firstName: String, middleName: String, lastName: String, gender: Gender, isVerified: Boolean, userImage: Array[Byte]) {
}
