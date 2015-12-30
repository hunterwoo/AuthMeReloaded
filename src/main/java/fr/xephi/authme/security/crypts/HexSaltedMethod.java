package fr.xephi.authme.security.crypts;

import fr.xephi.authme.security.RandomString;
import fr.xephi.authme.security.crypts.description.HasSalt;
import fr.xephi.authme.security.crypts.description.Recommendation;
import fr.xephi.authme.security.crypts.description.SaltType;
import fr.xephi.authme.security.crypts.description.Usage;

/**
 * Common type for encryption methods which use a random String of hexadecimal characters
 * and store the salt with the hash itself.
 */
@Recommendation(Usage.ACCEPTABLE)
@HasSalt(SaltType.TEXT) // See saltLength() for length
public abstract class HexSaltedMethod implements EncryptionMethod {

    public abstract int getSaltLength();

    @Override
    public abstract String computeHash(String password, String salt, String name);

    @Override
    public EncryptedPassword computeHash(String password, String name) {
        String salt = generateSalt();
        return new EncryptedPassword(computeHash(password, salt, null));
    }

    @Override
    public abstract boolean comparePassword(String password, EncryptedPassword encryptedPassword, String name);

    @Override
    public String generateSalt() {
        return RandomString.generateHex(getSaltLength());
    }

    @Override
    public boolean hasSeparateSalt() {
        return false;
    }
}