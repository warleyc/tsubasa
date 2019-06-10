/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MEncountersCommandCompatibilityDeleteDialogComponent } from 'app/entities/m-encounters-command-compatibility/m-encounters-command-compatibility-delete-dialog.component';
import { MEncountersCommandCompatibilityService } from 'app/entities/m-encounters-command-compatibility/m-encounters-command-compatibility.service';

describe('Component Tests', () => {
  describe('MEncountersCommandCompatibility Management Delete Component', () => {
    let comp: MEncountersCommandCompatibilityDeleteDialogComponent;
    let fixture: ComponentFixture<MEncountersCommandCompatibilityDeleteDialogComponent>;
    let service: MEncountersCommandCompatibilityService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MEncountersCommandCompatibilityDeleteDialogComponent]
      })
        .overrideTemplate(MEncountersCommandCompatibilityDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MEncountersCommandCompatibilityDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MEncountersCommandCompatibilityService);
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
