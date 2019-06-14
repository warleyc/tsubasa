/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MGuildRoulettePrizeDetailComponent } from 'app/entities/m-guild-roulette-prize/m-guild-roulette-prize-detail.component';
import { MGuildRoulettePrize } from 'app/shared/model/m-guild-roulette-prize.model';

describe('Component Tests', () => {
  describe('MGuildRoulettePrize Management Detail Component', () => {
    let comp: MGuildRoulettePrizeDetailComponent;
    let fixture: ComponentFixture<MGuildRoulettePrizeDetailComponent>;
    const route = ({ data: of({ mGuildRoulettePrize: new MGuildRoulettePrize(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MGuildRoulettePrizeDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MGuildRoulettePrizeDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MGuildRoulettePrizeDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mGuildRoulettePrize).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
