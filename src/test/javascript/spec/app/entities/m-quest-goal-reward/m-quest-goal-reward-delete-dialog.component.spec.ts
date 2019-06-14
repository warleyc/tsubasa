/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MQuestGoalRewardDeleteDialogComponent } from 'app/entities/m-quest-goal-reward/m-quest-goal-reward-delete-dialog.component';
import { MQuestGoalRewardService } from 'app/entities/m-quest-goal-reward/m-quest-goal-reward.service';

describe('Component Tests', () => {
  describe('MQuestGoalReward Management Delete Component', () => {
    let comp: MQuestGoalRewardDeleteDialogComponent;
    let fixture: ComponentFixture<MQuestGoalRewardDeleteDialogComponent>;
    let service: MQuestGoalRewardService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MQuestGoalRewardDeleteDialogComponent]
      })
        .overrideTemplate(MQuestGoalRewardDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MQuestGoalRewardDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MQuestGoalRewardService);
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
