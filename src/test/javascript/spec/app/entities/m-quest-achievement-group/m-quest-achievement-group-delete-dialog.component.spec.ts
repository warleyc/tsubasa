/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MQuestAchievementGroupDeleteDialogComponent } from 'app/entities/m-quest-achievement-group/m-quest-achievement-group-delete-dialog.component';
import { MQuestAchievementGroupService } from 'app/entities/m-quest-achievement-group/m-quest-achievement-group.service';

describe('Component Tests', () => {
  describe('MQuestAchievementGroup Management Delete Component', () => {
    let comp: MQuestAchievementGroupDeleteDialogComponent;
    let fixture: ComponentFixture<MQuestAchievementGroupDeleteDialogComponent>;
    let service: MQuestAchievementGroupService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MQuestAchievementGroupDeleteDialogComponent]
      })
        .overrideTemplate(MQuestAchievementGroupDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MQuestAchievementGroupDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MQuestAchievementGroupService);
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
