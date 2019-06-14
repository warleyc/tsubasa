/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MPvpWaveUpdateComponent } from 'app/entities/m-pvp-wave/m-pvp-wave-update.component';
import { MPvpWaveService } from 'app/entities/m-pvp-wave/m-pvp-wave.service';
import { MPvpWave } from 'app/shared/model/m-pvp-wave.model';

describe('Component Tests', () => {
  describe('MPvpWave Management Update Component', () => {
    let comp: MPvpWaveUpdateComponent;
    let fixture: ComponentFixture<MPvpWaveUpdateComponent>;
    let service: MPvpWaveService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MPvpWaveUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MPvpWaveUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MPvpWaveUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MPvpWaveService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MPvpWave(123);
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
        const entity = new MPvpWave();
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
