/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MCutShootOrbitDeleteDialogComponent } from 'app/entities/m-cut-shoot-orbit/m-cut-shoot-orbit-delete-dialog.component';
import { MCutShootOrbitService } from 'app/entities/m-cut-shoot-orbit/m-cut-shoot-orbit.service';

describe('Component Tests', () => {
  describe('MCutShootOrbit Management Delete Component', () => {
    let comp: MCutShootOrbitDeleteDialogComponent;
    let fixture: ComponentFixture<MCutShootOrbitDeleteDialogComponent>;
    let service: MCutShootOrbitService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MCutShootOrbitDeleteDialogComponent]
      })
        .overrideTemplate(MCutShootOrbitDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MCutShootOrbitDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MCutShootOrbitService);
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
