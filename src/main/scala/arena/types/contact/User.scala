package arena.types.contact

import arena.types.foundation.Gender.Gender

/**
 * @author Bhupendra Bhudia <bhupendra.bhudia@quedex.co.uk>
 *         18/11/2015 13:21
 */
case class User(firstName: String, middleName: String, lastName: String, gender: Gender, isVerified: Boolean, userImage: Array[Byte]) {
}
