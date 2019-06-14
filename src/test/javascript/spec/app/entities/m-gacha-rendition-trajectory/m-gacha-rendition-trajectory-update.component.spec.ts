/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MGachaRenditionTrajectoryUpdateComponent } from 'app/entities/m-gacha-rendition-trajectory/m-gacha-rendition-trajectory-update.component';
import { MGachaRenditionTrajectoryService } from 'app/entities/m-gacha-rendition-trajectory/m-gacha-rendition-trajectory.service';
import { MGachaRenditionTrajectory } from 'app/shared/model/m-gacha-rendition-trajectory.model';

describe('Component Tests', () => {
  describe('MGachaRenditionTrajectory Management Update Component', () => {
    let comp: MGachaRenditionTrajectoryUpdateComponent;
    let fixture: ComponentFixture<MGachaRenditionTrajectoryUpdateComponent>;
    let service: MGachaRenditionTrajectoryService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MGachaRenditionTrajectoryUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MGachaRenditionTrajectoryUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MGachaRenditionTrajectoryUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MGachaRenditionTrajectoryService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MGachaRenditionTrajectory(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new MGachaRenditionTrajectory();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
