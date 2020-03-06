package org.thoughtcrime.securesms.jobs;

import android.app.Application;

import androidx.annotation.NonNull;

import org.thoughtcrime.securesms.database.DatabaseFactory;
import org.thoughtcrime.securesms.jobmanager.Constraint;
import org.thoughtcrime.securesms.jobmanager.ConstraintObserver;
import org.thoughtcrime.securesms.jobmanager.Job;
import org.thoughtcrime.securesms.jobmanager.JobMigration;
import org.thoughtcrime.securesms.jobmanager.impl.CellServiceConstraint;
import org.thoughtcrime.securesms.jobmanager.impl.CellServiceConstraintObserver;
import org.thoughtcrime.securesms.jobmanager.impl.MasterSecretConstraint;
import org.thoughtcrime.securesms.jobmanager.impl.MasterSecretConstraintObserver;
import org.thoughtcrime.securesms.jobmanager.impl.NetworkConstraint;
import org.thoughtcrime.securesms.jobmanager.impl.NetworkConstraintObserver;
import org.thoughtcrime.securesms.jobmanager.impl.NetworkOrCellServiceConstraint;
import org.thoughtcrime.securesms.jobmanager.migrations.RecipientIdFollowUpJobMigration;
import org.thoughtcrime.securesms.jobmanager.migrations.RecipientIdFollowUpJobMigration2;
import org.thoughtcrime.securesms.jobmanager.migrations.RecipientIdJobMigration;
import org.thoughtcrime.securesms.jobmanager.migrations.SendReadReceiptsJobMigration;
import org.thoughtcrime.securesms.migrations.DatabaseMigrationJob;
import org.thoughtcrime.securesms.migrations.MigrationCompleteJob;
import org.thoughtcrime.securesms.migrations.RegistrationPinV2MigrationJob;
import org.thoughtcrime.securesms.migrations.StickerAdditionMigrationJob;
import org.thoughtcrime.securesms.migrations.StorageKeyRotationMigrationJob;
import org.thoughtcrime.securesms.migrations.StorageServiceMigrationJob;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class JobManagerFactories {

  public static Map<String, Job.Factory> getJobFactories(@NonNull Application application) {
    return new HashMap<String, Job.Factory>() {{
      put(AttachmentCopyJob.KEY,                     new AttachmentCopyJob.Factory());
      put(AttachmentDownloadJob.KEY,                 new AttachmentDownloadJob.Factory());
      put(AttachmentUploadJob.KEY,                   new AttachmentUploadJob.Factory());
      put(AttachmentMarkUploadedJob.KEY,             new AttachmentMarkUploadedJob.Factory());
      put(AttachmentCompressionJob.KEY,              new AttachmentCompressionJob.Factory());
      put(AvatarDownloadJob.KEY,                     new AvatarDownloadJob.Factory());
      put(CleanPreKeysJob.KEY,                       new CleanPreKeysJob.Factory());
      put(CreateSignedPreKeyJob.KEY,                 new CreateSignedPreKeyJob.Factory());
      put(DirectoryRefreshJob.KEY,                   new DirectoryRefreshJob.Factory());
      put(FcmRefreshJob.KEY,                         new FcmRefreshJob.Factory());
      put(LeaveGroupJob.KEY,                         new LeaveGroupJob.Factory());
      put(LocalBackupJob.KEY,                        new LocalBackupJob.Factory());
      put(MmsDownloadJob.KEY,                        new MmsDownloadJob.Factory());
      put(MultiDeviceBlockedUpdateJob.KEY,           new MultiDeviceBlockedUpdateJob.Factory());
      put(MultiDeviceConfigurationUpdateJob.KEY,     new MultiDeviceConfigurationUpdateJob.Factory());
      put(MultiDeviceContactUpdateJob.KEY,           new MultiDeviceContactUpdateJob.Factory());
      put(MultiDeviceGroupUpdateJob.KEY,             new MultiDeviceGroupUpdateJob.Factory());
      put(MultiDeviceKeysUpdateJob.KEY,              new MultiDeviceKeysUpdateJob.Factory());
      put(MultiDeviceMessageRequestResponseJob.KEY,  new MultiDeviceMessageRequestResponseJob.Factory());
      put(MultiDeviceProfileContentUpdateJob.KEY,    new MultiDeviceProfileContentUpdateJob.Factory());
      put(MultiDeviceProfileKeyUpdateJob.KEY,        new MultiDeviceProfileKeyUpdateJob.Factory());
      put(MultiDeviceReadUpdateJob.KEY,              new MultiDeviceReadUpdateJob.Factory());
      put(MultiDeviceStickerPackOperationJob.KEY,    new MultiDeviceStickerPackOperationJob.Factory());
      put(MultiDeviceStickerPackSyncJob.KEY,         new MultiDeviceStickerPackSyncJob.Factory());
      put(MultiDeviceStorageSyncRequestJob.KEY,      new MultiDeviceStorageSyncRequestJob.Factory());
      put(MultiDeviceVerifiedUpdateJob.KEY,          new MultiDeviceVerifiedUpdateJob.Factory());
      put(MultiDeviceViewOnceOpenJob.KEY,            new MultiDeviceViewOnceOpenJob.Factory());
      put(PushDecryptMessageJob.KEY,                 new PushDecryptMessageJob.Factory());
      put(PushProcessMessageJob.KEY,                 new PushProcessMessageJob.Factory());
      put(PushGroupSendJob.KEY,                      new PushGroupSendJob.Factory());
      put(PushGroupUpdateJob.KEY,                    new PushGroupUpdateJob.Factory());
      put(PushMediaSendJob.KEY,                      new PushMediaSendJob.Factory());
      put(PushNotificationReceiveJob.KEY,            new PushNotificationReceiveJob.Factory());
      put(PushTextSendJob.KEY,                       new PushTextSendJob.Factory());
      put(ReactionSendJob.KEY,                       new ReactionSendJob.Factory());
      put(RefreshAttributesJob.KEY,                  new RefreshAttributesJob.Factory());
      put(RefreshOwnProfileJob.KEY,                  new RefreshOwnProfileJob.Factory());
      put(RefreshPreKeysJob.KEY,                     new RefreshPreKeysJob.Factory());
      put(RemoteConfigRefreshJob.KEY,                new RemoteConfigRefreshJob.Factory());
      put(RequestGroupInfoJob.KEY,                   new RequestGroupInfoJob.Factory());
      put(RetrieveProfileAvatarJob.KEY,              new RetrieveProfileAvatarJob.Factory());
      put(RetrieveProfileJob.KEY,                    new RetrieveProfileJob.Factory());
      put(RotateCertificateJob.KEY,                  new RotateCertificateJob.Factory());
      put(RotateProfileKeyJob.KEY,                   new RotateProfileKeyJob.Factory());
      put(RotateSignedPreKeyJob.KEY,                 new RotateSignedPreKeyJob.Factory());
      put(SendDeliveryReceiptJob.KEY,                new SendDeliveryReceiptJob.Factory());
      put(SendReadReceiptJob.KEY,                    new SendReadReceiptJob.Factory(application));
      put(ServiceOutageDetectionJob.KEY,             new ServiceOutageDetectionJob.Factory());
      put(StickerDownloadJob.KEY,                    new StickerDownloadJob.Factory());
      put(StickerPackDownloadJob.KEY,                new StickerPackDownloadJob.Factory());
      put(StorageForcePushJob.KEY,                   new StorageForcePushJob.Factory());
      put(StorageSyncJob.KEY,                        new StorageSyncJob.Factory());
      put(TrimThreadJob.KEY,                         new TrimThreadJob.Factory());
      put(TypingSendJob.KEY,                         new TypingSendJob.Factory());
      put(UpdateApkJob.KEY,                          new UpdateApkJob.Factory());
      put(MarkerJob.KEY,                             new MarkerJob.Factory());
      put(ProfileUploadJob.KEY,                      new ProfileUploadJob.Factory());

      // Migrations
      put(DatabaseMigrationJob.KEY,                  new DatabaseMigrationJob.Factory());
      put(MigrationCompleteJob.KEY,                  new MigrationCompleteJob.Factory());
      put(RegistrationPinV2MigrationJob.KEY,         new RegistrationPinV2MigrationJob.Factory());
      put(StickerAdditionMigrationJob.KEY,           new StickerAdditionMigrationJob.Factory());
      put(StorageKeyRotationMigrationJob.KEY,        new StorageKeyRotationMigrationJob.Factory());
      put(StorageServiceMigrationJob.KEY,            new StorageServiceMigrationJob.Factory());

      // Dead jobs
      put("PushContentReceiveJob",                   new FailingJob.Factory());
      put("AttachmentUploadJob",                     new FailingJob.Factory());
      put("MmsSendJob",                              new FailingJob.Factory());
      put("RefreshUnidentifiedDeliveryAbilityJob",   new FailingJob.Factory());
    }};
  }

  public static Map<String, Constraint.Factory> getConstraintFactories(@NonNull Application application) {
    return new HashMap<String, Constraint.Factory>() {{
      put(CellServiceConstraint.KEY,          new CellServiceConstraint.Factory(application));
      put(MasterSecretConstraint.KEY,         new MasterSecretConstraint.Factory(application));
      put(NetworkConstraint.KEY,              new NetworkConstraint.Factory(application));
      put(NetworkOrCellServiceConstraint.KEY, new NetworkOrCellServiceConstraint.Factory(application));
    }};
  }

  public static List<ConstraintObserver> getConstraintObservers(@NonNull Application application) {
    return Arrays.asList(CellServiceConstraintObserver.getInstance(application),
                         new NetworkConstraintObserver(application),
                         new MasterSecretConstraintObserver(application));
  }

  public static List<JobMigration> getJobMigrations(@NonNull Application application) {
    return Arrays.asList(new RecipientIdJobMigration(application),
                         new RecipientIdFollowUpJobMigration(),
                         new RecipientIdFollowUpJobMigration2(),
                         new SendReadReceiptsJobMigration(DatabaseFactory.getMmsSmsDatabase(application)));
  }
}
