/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MMatchEnvironmentDeleteDialogComponent } from 'app/entities/m-match-environment/m-match-environment-delete-dialog.component';
import { MMatchEnvironmentService } from 'app/entities/m-match-environment/m-match-environment.service';

describe('Component Tests', () => {
  describe('MMatchEnvironment Management Delete Component', () => {
    let comp: MMatchEnvironmentDeleteDialogComponent;
    let fixture: ComponentFixture<MMatchEnvironmentDeleteDialogComponent>;
    let service: MMatchEnvironmentService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MMatchEnvironmentDeleteDialogComponent]
      })
        .overrideTemplate(MMatchEnvironmentDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MMatchEnvironmentDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MMatchEnvironmentService);
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
