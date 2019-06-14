/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MGachaRenditionTrajectoryPhoenixUpdateComponent } from 'app/entities/m-gacha-rendition-trajectory-phoenix/m-gacha-rendition-trajectory-phoenix-update.component';
import { MGachaRenditionTrajectoryPhoenixService } from 'app/entities/m-gacha-rendition-trajectory-phoenix/m-gacha-rendition-trajectory-phoenix.service';
import { MGachaRenditionTrajectoryPhoenix } from 'app/shared/model/m-gacha-rendition-trajectory-phoenix.model';

describe('Component Tests', () => {
  describe('MGachaRenditionTrajectoryPhoenix Management Update Component', () => {
    let comp: MGachaRenditionTrajectoryPhoenixUpdateComponent;
    let fixture: ComponentFixture<MGachaRenditionTrajectoryPhoenixUpdateComponent>;
    let service: MGachaRenditionTrajectoryPhoenixService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MGachaRenditionTrajectoryPhoenixUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MGachaRenditionTrajectoryPhoenixUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MGachaRenditionTrajectoryPhoenixUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MGachaRenditionTrajectoryPhoenixService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MGachaRenditionTrajectoryPhoenix(123);
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
        const entity = new MGachaRenditionTrajectoryPhoenix();
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
