/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MMarathonEffectiveCardDeleteDialogComponent } from 'app/entities/m-marathon-effective-card/m-marathon-effective-card-delete-dialog.component';
import { MMarathonEffectiveCardService } from 'app/entities/m-marathon-effective-card/m-marathon-effective-card.service';

describe('Component Tests', () => {
  describe('MMarathonEffectiveCard Management Delete Component', () => {
    let comp: MMarathonEffectiveCardDeleteDialogComponent;
    let fixture: ComponentFixture<MMarathonEffectiveCardDeleteDialogComponent>;
    let service: MMarathonEffectiveCardService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MMarathonEffectiveCardDeleteDialogComponent]
      })
        .overrideTemplate(MMarathonEffectiveCardDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MMarathonEffectiveCardDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MMarathonEffectiveCardService);
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
