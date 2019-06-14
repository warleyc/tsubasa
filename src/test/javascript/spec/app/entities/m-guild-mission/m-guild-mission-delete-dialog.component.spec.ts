/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MGuildMissionDeleteDialogComponent } from 'app/entities/m-guild-mission/m-guild-mission-delete-dialog.component';
import { MGuildMissionService } from 'app/entities/m-guild-mission/m-guild-mission.service';

describe('Component Tests', () => {
  describe('MGuildMission Management Delete Component', () => {
    let comp: MGuildMissionDeleteDialogComponent;
    let fixture: ComponentFixture<MGuildMissionDeleteDialogComponent>;
    let service: MGuildMissionService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MGuildMissionDeleteDialogComponent]
      })
        .overrideTemplate(MGuildMissionDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MGuildMissionDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MGuildMissionService);
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
