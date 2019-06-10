/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MFullPowerPointUpdateComponent } from 'app/entities/m-full-power-point/m-full-power-point-update.component';
import { MFullPowerPointService } from 'app/entities/m-full-power-point/m-full-power-point.service';
import { MFullPowerPoint } from 'app/shared/model/m-full-power-point.model';

describe('Component Tests', () => {
  describe('MFullPowerPoint Management Update Component', () => {
    let comp: MFullPowerPointUpdateComponent;
    let fixture: ComponentFixture<MFullPowerPointUpdateComponent>;
    let service: MFullPowerPointService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MFullPowerPointUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MFullPowerPointUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MFullPowerPointUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MFullPowerPointService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MFullPowerPoint(123);
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
        const entity = new MFullPowerPoint();
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
