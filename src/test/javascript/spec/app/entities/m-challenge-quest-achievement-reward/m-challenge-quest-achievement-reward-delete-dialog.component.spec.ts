/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MChallengeQuestAchievementRewardDeleteDialogComponent } from 'app/entities/m-challenge-quest-achievement-reward/m-challenge-quest-achievement-reward-delete-dialog.component';
import { MChallengeQuestAchievementRewardService } from 'app/entities/m-challenge-quest-achievement-reward/m-challenge-quest-achievement-reward.service';

describe('Component Tests', () => {
  describe('MChallengeQuestAchievementReward Management Delete Component', () => {
    let comp: MChallengeQuestAchievementRewardDeleteDialogComponent;
    let fixture: ComponentFixture<MChallengeQuestAchievementRewardDeleteDialogComponent>;
    let service: MChallengeQuestAchievementRewardService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MChallengeQuestAchievementRewardDeleteDialogComponent]
      })
        .overrideTemplate(MChallengeQuestAchievementRewardDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MChallengeQuestAchievementRewardDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MChallengeQuestAchievementRewardService);
      mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
      mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
    });

    describe('confirmDelete', () => {
      it('Should call delete service on confirmDelete', inject(
        [],
        fakeAsync(() => {
          // GIVEN
          spyOn(service, 'delete').and.returnValue(of({}));

          // WHEN
          comp.confirmDelete(123);
          tick();

          // THEN
          expect(service.delete).toHaveBeenCalledWith(123);
          expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
          expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
        })
      ));
    });
  });
});
