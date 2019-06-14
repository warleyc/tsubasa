/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MQuestCoopRewardDeleteDialogComponent } from 'app/entities/m-quest-coop-reward/m-quest-coop-reward-delete-dialog.component';
import { MQuestCoopRewardService } from 'app/entities/m-quest-coop-reward/m-quest-coop-reward.service';

describe('Component Tests', () => {
  describe('MQuestCoopReward Management Delete Component', () => {
    let comp: MQuestCoopRewardDeleteDialogComponent;
    let fixture: ComponentFixture<MQuestCoopRewardDeleteDialogComponent>;
    let service: MQuestCoopRewardService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MQuestCoopRewardDeleteDialogComponent]
      })
        .overrideTemplate(MQuestCoopRewardDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MQuestCoopRewardDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MQuestCoopRewardService);
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
