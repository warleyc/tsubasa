/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MQuestRewardGroupDeleteDialogComponent } from 'app/entities/m-quest-reward-group/m-quest-reward-group-delete-dialog.component';
import { MQuestRewardGroupService } from 'app/entities/m-quest-reward-group/m-quest-reward-group.service';

describe('Component Tests', () => {
  describe('MQuestRewardGroup Management Delete Component', () => {
    let comp: MQuestRewardGroupDeleteDialogComponent;
    let fixture: ComponentFixture<MQuestRewardGroupDeleteDialogComponent>;
    let service: MQuestRewardGroupService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MQuestRewardGroupDeleteDialogComponent]
      })
        .overrideTemplate(MQuestRewardGroupDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MQuestRewardGroupDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MQuestRewardGroupService);
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
