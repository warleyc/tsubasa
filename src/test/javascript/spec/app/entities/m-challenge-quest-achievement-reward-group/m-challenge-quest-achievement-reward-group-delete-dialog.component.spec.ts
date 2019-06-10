/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MChallengeQuestAchievementRewardGroupDeleteDialogComponent } from 'app/entities/m-challenge-quest-achievement-reward-group/m-challenge-quest-achievement-reward-group-delete-dialog.component';
import { MChallengeQuestAchievementRewardGroupService } from 'app/entities/m-challenge-quest-achievement-reward-group/m-challenge-quest-achievement-reward-group.service';

describe('Component Tests', () => {
  describe('MChallengeQuestAchievementRewardGroup Management Delete Component', () => {
    let comp: MChallengeQuestAchievementRewardGroupDeleteDialogComponent;
    let fixture: ComponentFixture<MChallengeQuestAchievementRewardGroupDeleteDialogComponent>;
    let service: MChallengeQuestAchievementRewardGroupService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MChallengeQuestAchievementRewardGroupDeleteDialogComponent]
      })
        .overrideTemplate(MChallengeQuestAchievementRewardGroupDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MChallengeQuestAchievementRewardGroupDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MChallengeQuestAchievementRewardGroupService);
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
