/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MStoryResourceMappingDeleteDialogComponent } from 'app/entities/m-story-resource-mapping/m-story-resource-mapping-delete-dialog.component';
import { MStoryResourceMappingService } from 'app/entities/m-story-resource-mapping/m-story-resource-mapping.service';

describe('Component Tests', () => {
  describe('MStoryResourceMapping Management Delete Component', () => {
    let comp: MStoryResourceMappingDeleteDialogComponent;
    let fixture: ComponentFixture<MStoryResourceMappingDeleteDialogComponent>;
    let service: MStoryResourceMappingService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MStoryResourceMappingDeleteDialogComponent]
      })
        .overrideTemplate(MStoryResourceMappingDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MStoryResourceMappingDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MStoryResourceMappingService);
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
