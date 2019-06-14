/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MPvpRegulationDeleteDialogComponent } from 'app/entities/m-pvp-regulation/m-pvp-regulation-delete-dialog.component';
import { MPvpRegulationService } from 'app/entities/m-pvp-regulation/m-pvp-regulation.service';

describe('Component Tests', () => {
  describe('MPvpRegulation Management Delete Component', () => {
    let comp: MPvpRegulationDeleteDialogComponent;
    let fixture: ComponentFixture<MPvpRegulationDeleteDialogComponent>;
    let service: MPvpRegulationService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MPvpRegulationDeleteDialogComponent]
      })
        .overrideTemplate(MPvpRegulationDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MPvpRegulationDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MPvpRegulationService);
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
