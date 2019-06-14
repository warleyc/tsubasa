/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MMarathonQuestStageRewardDeleteDialogComponent } from 'app/entities/m-marathon-quest-stage-reward/m-marathon-quest-stage-reward-delete-dialog.component';
import { MMarathonQuestStageRewardService } from 'app/entities/m-marathon-quest-stage-reward/m-marathon-quest-stage-reward.service';

describe('Component Tests', () => {
  describe('MMarathonQuestStageReward Management Delete Component', () => {
    let comp: MMarathonQuestStageRewardDeleteDialogComponent;
    let fixture: ComponentFixture<MMarathonQuestStageRewardDeleteDialogComponent>;
    let service: MMarathonQuestStageRewardService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MMarathonQuestStageRewardDeleteDialogComponent]
      })
        .overrideTemplate(MMarathonQuestStageRewardDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MMarathonQuestStageRewardDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MMarathonQuestStageRewardService);
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
